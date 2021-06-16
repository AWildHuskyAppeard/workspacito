package tw.group5.model;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pictureService")
@Transactional
public class PictureService {
	@Autowired
	private PictureDao pictureDao;

	public Picture insert(Picture bean) {
		return pictureDao.insert(bean);
	}

}
