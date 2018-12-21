package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    public List<Album> getAllAlbum(Integer page,Integer rows);
    public void regist(Album album);

}
