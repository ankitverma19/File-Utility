package com.dbs.test.file.controller.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContentDetails {

    private String path;
    private boolean directory;
    private long size;
    private List<ContentDetails> contents;
    private List<FileAttributesType> attributes;

    public ContentDetails(final String path,
                          final boolean directory,
                          final long size,
                          final List<ContentDetails> contents,
                          final List<FileAttributesType> attributes) {

        this.path = path;
        this.directory = directory;
        this.size = size;
        this.contents = contents == null ? new ArrayList<>() : contents;
        this.attributes = attributes == null ? new ArrayList<>() : attributes;
    }

    public ContentDetails(final String path,
                          final boolean directory,
                          final long size,
                          final List<FileAttributesType> attributes) {

        this.path = path;
        this.directory = directory;
        this.size = size;
        this.contents = null;
        this.attributes = attributes == null ? new ArrayList<>() : attributes;
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public List<ContentDetails> getContents() {
        return contents;
    }

    public List<FileAttributesType> getAttributes() {
        return attributes;
    }

    public long getSize() {
        return size;
    }

    public void addContent(final ContentDetails contentDetails) {
        if (contentDetails != null) {
            this.contents.add(contentDetails);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentDetails that = (ContentDetails) o;
        return directory == that.directory &&
                size == that.size &&
                path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, directory, size);
    }

    @Override
    public String toString() {
        return "ContentDetails{" +
                "path='" + path + '\'' +
                ", directory=" + directory +
                ", size=" + size +
                ", contents=" + contents +
                ", attributes=" + attributes +
                '}';
    }
}
