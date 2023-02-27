package com.simo.ugmate.tools;

import java.io.File;
import java.util.Comparator;

/**
 * 按照文件的最后修改时间进行比较的比较器。
 * @author root 蔡火胜。
 *
 */
public class FileLastModifiedTimeComparator implements Comparator<File> 
{

	@Override
	/**
	 * 比较两个文件。
	 */
	public int compare(File lhs, File rhs) 
	{
	
		return (int)(lhs.lastModified()-rhs.lastModified());
	} //public int compare(File lhs, File rhs)

} //public class FileLastModifiedTimeComparator implements Comparator<File>
