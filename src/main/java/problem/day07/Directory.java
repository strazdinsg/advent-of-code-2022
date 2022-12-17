package problem.day07;

import java.util.HashMap;
import java.util.Map;
import tools.Logger;

/**
 * A directory within the file system.
 */
public class Directory {
  public static final String ROOT_NAME = "/";
  public static final String PARENT_DIR = "..";
  private Directory parent;
  private final String name;
  private final Map<String, Directory> subDirectories = new HashMap<>();
  private final Map<String, Integer> fileSizes = new HashMap<>();

  private static long totalFileSizeSum;

  private static long minSizeFound;

  private Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  /**
   * Create the root directory.
   *
   * @return Root directory
   */
  public static Directory createRoot() {
    return new Directory(ROOT_NAME, null);
  }

  public static void resetTotalSize() {
    totalFileSizeSum = 0;
  }

  public static long getTotalSizeSum() {
    return totalFileSizeSum;
  }

  public static void resetMinSize() {
    minSizeFound = Long.MAX_VALUE;
  }

  public static long getMinSize() {
    return minSizeFound;
  }

  /**
   * Get the child directory with given name.
   *
   * @param subDirectoryName The name of the child directory
   * @return The child directory or null if none found
   */
  public Directory getChildDirectory(String subDirectoryName) {
    return subDirectories.get(subDirectoryName);
  }

  /**
   * Add a new child directory.
   *
   * @param directoryName Name of the child directory
   * @throws IllegalArgumentException When directory with this name already exists
   */
  public void addChildDirectory(String directoryName) throws IllegalArgumentException {
    if (getChildDirectory(directoryName) != null) {
      throw new IllegalArgumentException("Can't add a duplicate directory: " + directoryName);
    }

    subDirectories.put(directoryName, new Directory(directoryName, this));
  }

  /**
   * Add a new file to this directory.
   *
   * @param fileInfo Info of the file to add
   */
  public void addFile(FileInfo fileInfo) {
    if (fileExists(fileInfo.getName())) {
      throw new IllegalArgumentException("File " + fileInfo.getName() + " already exists!");
    }
    fileSizes.put(fileInfo.getName(), fileInfo.getSize());
  }

  private boolean fileExists(String filename) {
    return fileSizes.containsKey(filename);
  }

  /**
   * Count the number of directories not exceeding maxDirSize. To get the result, call the
   * getTotalSizeSum() method afterwards!
   *
   * @param maxDirSize Maximum directory size to count in the final sum.
   * @return The number of bytes inside this directory, including all files and subdirectories.
   *     Warning: this does NOT exclude all subdirectories larger than the allowed size.
   *     This value is needed for the recursive calls.
   */
  public long countSumOfDirsNotExceeding(long maxDirSize) {
    long sum = getTotalFileSize() + totalSubdirectorySize(maxDirSize);
    if (sum <= maxDirSize) {
      totalFileSizeSum += sum;
    }
    return sum;
  }

  private long totalSubdirectorySize(long maxDirSize) {
    long sum = 0;
    for (Directory subDir : subDirectories.values()) {
      sum += subDir.countSumOfDirsNotExceeding(maxDirSize);
    }
    return sum;
  }

  private long getTotalFileSize() {
    long sum = 0;
    for (Integer size : fileSizes.values()) {
      sum += size;
    }
    return sum;
  }

  public Directory getParent() {
    return parent;
  }

  /**
   * Return the full path of the directory. Can be used for debugging.
   *
   * @return The full path
   */
  public String getFullPath() {
    if (name.equals(ROOT_NAME)) {
      return "";
    } else {
      String parentPath = parent != null ? parent.getFullPath() : "";
      return parentPath + "/" + name;
    }
  }

  /**
   * Search for the smallest directory exceeding minSize.
   *
   * @param minSize Minimum size for the directory to look for
   * @return The size of this directory. Used for recursive calls.
   *     Note: the result (the minimum size) can be found by calling the getMinSize() method
   *     afterwards.
   */
  public long searchSmallestDirExceeding(long minSize) {
    long subDirSum = 0;
    for (Directory subDir : subDirectories.values()) {
      long subDirSize = subDir.searchSmallestDirExceeding(minSize);
      subDirSum += subDirSize;
      if (subDirSize >= minSize && subDirSize < minSizeFound) {
        minSizeFound = subDirSize;
      }
    }

    long thisDirSize = getTotalFileSize() + subDirSum;
    if (thisDirSize >= minSize
        && (minSizeFound == Long.MAX_VALUE || thisDirSize < minSizeFound)) {
      minSizeFound = thisDirSize;
      Logger.info("Size of " + getFullPath() + ": " + minSizeFound);
    }

    return thisDirSize;
  }
}
