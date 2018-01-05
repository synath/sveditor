package net.sf.sveditor.core.db.index;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.sveditor.core.db.attr.SVDBDoNotSaveAttr;

/**
 * Contains index data for a given root file
 * 
 * @author ballance
 *
 */
public class SVDBFileCacheData {
	public long								fLastParseTimestamp;
	/*
	 * @see ISVDBDeclCache
	 */
	public int								fFileAttr;
	public int								fFileId;
	public Set<Integer>						fIncludedFiles;
	public Set<String>						fMissingIncludeFiles;
	
	// Declarations that might be visible without
	// a leading qualifier (eg packages, classes, tasks/functions, etc)
	@SVDBDoNotSaveAttr
	public List<SVDBDeclCacheItem>			fTopLevelDeclarations;
	
	// Set of identifiers referenced within this root file
	// and any included files
	public Set<String>						fRefCache;

	public SVDBFileCacheData() {
		fIncludedFiles = new HashSet<Integer>();
		fMissingIncludeFiles = new HashSet<String>();
		fTopLevelDeclarations = new ArrayList<SVDBDeclCacheItem>();
		fRefCache = new HashSet<String>();
	}
	
	public SVDBFileCacheData(int id, int attr) {
		this();
		fFileId = id;
		fFileAttr = attr;
	}
	
	public long getLastParseTimestamp() { return fLastParseTimestamp; }
	
	public void setLastParseTimestamp(long ts) { fLastParseTimestamp = ts; }
	
	public int getFileAttr() { return fFileAttr; }
	
	public void setFileAttr(int attr) { fFileAttr = attr; }
	
	public int getFileId() { return fFileId; }
	
	public void setFileId(int id) { fFileId = id; }
	
	public Set<String> getRefCache() { return fRefCache; }
	
	public List<SVDBDeclCacheItem> getTopLevelDeclarations() { return fTopLevelDeclarations; }
	
	public Set<Integer> getIncludedFiles() { return fIncludedFiles; }
	
	public void addIncludedFile(int id) {
		fIncludedFiles.add(id);
	}
	
}
