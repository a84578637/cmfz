package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumPageDto;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    Logger logger;
    @Override
    public AlbumPageDto getAllAlbum(Integer page, Integer rows) {
        List<Album> albums = albumMapper.selectAllAlbum(page, rows);
            logger.info("Service中查询成功");

        int i = albumMapper.selectCount(new Album());
        AlbumPageDto albumPageDto = new AlbumPageDto(i, albums);
        return albumPageDto;
    }

    @Override
    public void regist(Album album ) {



        albumMapper.insert(album);
        logger.info("Service添加成功");
    }

    @Override
    public Album getOne(Integer id) {
        Album album = albumMapper.selectByPrimaryKey(id);

        return album;
    }
}
