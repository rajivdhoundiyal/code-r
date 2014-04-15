package com.codeproof.spec;

import com.codeproof.model.File;

public interface FileBusinessService {

	File find(String id);

	void save(File file);

	void update(File file);
	
}
