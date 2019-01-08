package com.baizhi.mapper;


import com.baizhi.util.LuceneUtil;
import com.baizhi.entity.Article;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LuceneArticleDao {
//创建索引
    public void createIndex(Article article) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromPro = getDocFromPro(article);
        try {
            indexWriter.addDocument(docFromPro);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    public void deleteIndex(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }

    }

    public void updateIndex(Article article) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromPro = getDocFromPro(article);
        try {
            indexWriter.updateDocument(new Term("id", article.getId().toString()), docFromPro);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }
        //提供，查询词、分页行数、分页页数
    public List<Article> SearcherIndex(String params,Integer pageSize,Integer pageNum) {
        //获得IndexSearcher对象
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        //初始化准备返回的结果集
        List<Article> list = null;
        try {
            //设置在哪些列查询
            String[] strs = {"title","content"};
            //新建多列搜索器，参数表为：版本号、列、分词器
            MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44,strs,new IKAnalyzer());
            //将查询句子传给分词多列搜索器
            Query query = multiFieldQueryParser.parse(params);
            //设置高亮格式替换器
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            //将分词后的查询词交给高亮格式替换器。
            Scorer scorer = new QueryScorer(query);
            //新建高亮器，参数为：高亮格式替换器、高亮词
            Highlighter highlighter = new Highlighter(formatter, scorer);

             //高亮+处理
            //开始根据查询词和页码进行查询
            TopDocs topDocs = indexSearcher.search(query,pageNum * pageSize);
            //获取查询结果ID集
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            list = new ArrayList<>();
            //根据查询结果ID集，分别进行搜索
            for (int i = (pageNum - 1) * pageSize; i < scoreDocs.length; i++) {
                //依次获得每一个查询结果ID
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                //根据ID查询到DOC
                Document document = indexSearcher.doc(doc);
                //高亮器高亮DOC中的相应列
                String bestFragmentTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("title"));
                String bestFragmentContent = highlighter.getBestFragment(new IKAnalyzer(), "content", document.get("content"));
                //将最终结果交给相应的结果类。添加到list中 提交上去
                Article article = getProFromDoc(document);
                if(bestFragmentTitle!=null){
                    article.setTitle(bestFragmentTitle);
                }
                if(bestFragmentContent!=null){
                    article.setContent(bestFragmentContent);
                }
                list.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Document getDocFromPro(Article product) {
        Document document = new Document();
        document.add(new StringField("id", product.getId().toString(), Field.Store.YES));
        document.add(new TextField("title", product.getTitle(), Field.Store.YES));
        document.add(new StringField("insert_img", product.getInsertImg(), Field.Store.YES));
        return document;
    }

    public Article getProFromDoc(Document document) {
        Article product = new Article();
          product.setId(Integer.valueOf(document.get("id")));
          product.setContent(document.get("contend"));
          product.setInsertImg(document.get("insert_img"));
          product.setTitle(document.get("title"));

        return product;
    }

}
