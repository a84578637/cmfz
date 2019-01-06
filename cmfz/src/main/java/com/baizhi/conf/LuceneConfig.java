package com.baizhi.conf;



import com.baizhi.mapper.LuceneArticleDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConfig {
    @Bean
    public LuceneArticleDao luceneArticleDao() {
        return new LuceneArticleDao();
    }

}
