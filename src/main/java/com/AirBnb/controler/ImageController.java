package com.AirBnb.controler;

import com.AirBnb.entity.Images;
import com.AirBnb.entity.Room;
import com.AirBnb.entity.AppUser;
import com.AirBnb.repository.ImagesRepository;
import com.AirBnb.repository.RoomRepository;
import com.AirBnb.service.Impl.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {

    private ImagesRepository imagesRepository;
    private RoomRepository roomRepository;
    private BucketService bucketService;

    public ImageController(ImagesRepository imagesRepository, RoomRepository roomRepository, BucketService bucketService) {
        this.imagesRepository = imagesRepository;
        this.roomRepository = roomRepository;
        this.bucketService = bucketService;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file,
                                             @PathVariable String bucketName,
                                             @PathVariable long roomId,
                                             @AuthenticationPrincipal AppUser user
                                             ) {
        String imageUrl = bucketService.uploadFile(file, bucketName);
        Room room = roomRepository.findById(roomId).get();
        Images img = new Images();
        img.setImageUrl(imageUrl);
        img.setRoom(room);
        img.setAppUser(user);

        Images savedImage = imagesRepository.save(img);

        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }
}
