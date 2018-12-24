package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumPageDto;

import java.util.List;

public interface AlbumService {
    public AlbumPageDto getAllAlbum(Integer page, Integer rows);
    public void regist(Album album);
    public Album getOne(Integer id);
    public List<Album> getPOI();

}
