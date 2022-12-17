package problem.day07;

/**
 * Represents the file system, with root directory, hierarchical directories and files.
 */
public class FileSystem {
  private final Directory root;
  private Directory workingDirectory;
  private boolean isListing;

  /**
   * Create a new file system.
   */
  public FileSystem() {
    root = Directory.createRoot();
    workingDirectory = root;
    isListing = false;
  }

  /**
   * Execute the given command.
   *
   * @param command The command to execute
   * @throws IllegalArgumentException When the command can't be executed in the current state
   */
  public void execute(Command command) throws IllegalArgumentException {
    if (command.getType() == CommandType.CD) {
      changeWorkingDirectory(command.getArgument());
    } else if (command.getType() == CommandType.LS) {
      setListingMode();
    }
  }

  private void changeWorkingDirectory(String subDirectoryName) {
    isListing = false;
    workingDirectory = switch (subDirectoryName) {
      case Directory.ROOT_NAME -> root;
      case Directory.PARENT_DIR -> workingDirectory.getParent();
      default -> workingDirectory.getChildDirectory(subDirectoryName);
    };

    if (workingDirectory == null) {
      throw new IllegalArgumentException("Child directory does not exist: " + subDirectoryName);
    }
  }

  private void setListingMode() {
    isListing = true;
  }

  /**
   * Add a content to the current directory - either a subdirectory or a file.
   *
   * @param contentInfo The string representing the content (file or directory)
   * @throws IllegalStateException If trying to add a content info while the last command was ls
   */
  public void addCurrentDirContent(String contentInfo) throws IllegalStateException {
    if (!isListing) {
      throw new IllegalStateException("Can't add directory content while not in listing mode");
    }

    if (isDirectoryInfo(contentInfo)) {
      addDirectory(contentInfo);
    } else {
      addFile(contentInfo);
    }
  }

  private void addFile(String fileInfoString) {
    workingDirectory.addFile(new FileInfo(fileInfoString));
  }

  private void addDirectory(String directoryInfo) {
    String directoryName = directoryInfo.substring(4);
    workingDirectory.addChildDirectory(directoryName);
  }

  private boolean isDirectoryInfo(String contentInfo) {
    return contentInfo.startsWith("dir ");
  }

  /**
   * Get the sum of directory sizes for all the directories which don't exceed the given size.
   *
   * @param maxDirSize Maximum allowed directory size. All directories with bigger sizes will not
   *                   be counted in the final sum.
   * @return The sum of sizes for all directories not exceeding the allowed size.
   */
  public long countSumOfDirectoriesNotExceeding(int maxDirSize) {
    Directory.resetTotalSize();
    root.countSumOfDirsNotExceeding(maxDirSize);
    return Directory.getTotalSizeSum();
  }

  /**
   * Get the total size of all files stored in the file system.
   *
   * @return The total size of all files
   */
  public long getTotalSize() {
    return root.countSumOfDirsNotExceeding(0);
  }

  /**
   * Get the size of the smallest directory exceeding the given threshold.
   *
   * @param minSize The minimum size to look for
   * @return The minimum size found
   */
  public long getSmallestDirExceeding(long minSize) {
    Directory.resetMinSize();
    root.searchSmallestDirExceeding(minSize);
    return Directory.getMinSize();
  }
}
