package com.block.web.builder.listeners;

import com.block.web.builder.core.WebFile;

public interface FileCreationListener {

  void onFileCreated(WebFile webFile);

  void onFileCreationFailed(String error);
}
