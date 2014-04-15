package com.codeproof.data.spec;

import com.codeproof.model.File;

public interface FileDataService {

	File find(String id);

	void save(File file);

	void update(File file);

}
