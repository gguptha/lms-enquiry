package pfs.lms.enquiry.vault;

import org.apache.tika.mime.MimeTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface FileStorage {
	byte[] download(UUID id);
	Optional<FilePointer> findFile(UUID uuid);
	UUID saveFile(MultipartFile file) throws IOException, MimeTypeException;
	UUID saveFile(File file) throws IOException, MimeTypeException;


}
