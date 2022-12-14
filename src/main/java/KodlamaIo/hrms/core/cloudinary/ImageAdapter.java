package KodlamaIo.hrms.core.cloudinary;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.ErrorDataResult;
import KodlamaIo.hrms.core.utilities.results.SuccessDataResult;

@Service
public class ImageAdapter implements ImageCheckService {

    private Cloudinary cloudinary;

    public ImageAdapter() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxhufnsb5",
                "api_key", "476692637945473",
                "api_secret", "BtxzTaIwIAvv0b-PZPUjTT10L3U"));
    }

    @Override
    public DataResult<Map> loadFile(MultipartFile image) {
        try {
            Map<String, String> uploadResult = (Map<String, String>) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            return new SuccessDataResult(uploadResult);
        } catch (IOException e) {
            return new ErrorDataResult(e.getMessage());
        }
    }
}