package com.suyang.mbg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

public class IOUtils {
    /**
     * 判断文件或文件夹是否存在
     *
     * @param path 路径
     * @return
     */
    public static boolean exists(String path) {
        File f = new File(path);
        return f.exists();
    }

    /**
     * 判断是否是文件
     *
     * @param path 文件路径
     * @return
     */
    public static boolean isFile(String path) {
        File f = new File(path);
        return f.isFile();
    }

    /**
     * 判断是否是文件目录
     *
     * @param path 文件目录路径
     * @return
     */
    public static boolean isDirectory(String path) {
        File f = new File(path);
        return f.isDirectory();
    }

    /**
     * 获取文件大小
     *
     * @param path 文件路径
     * @return
     */
    public static long getFileSize(String path) {
        long result = 0;
        File f = new File(path);
        if (f.isFile()) {
            result = f.length();
        }
        return result;
    }

    /**
     * 链接路径
     *
     * @param paths
     * @return
     */
    public static String combine(String... paths) {
        String result = "";
        for (String path : paths) {
            result = FilenameUtils.concat(result, path);
        }
        return result;
    }

    /**
     * 获取文件目录
     *
     * @param path
     * @return
     */
    public static String getDirectory(String path) {
        File file = new File(path);
        return file.getParent();
    }

    /**
     * 删除指定文件
     *
     * @param path    文件路径
     * @param pattern 文件名称匹配的正则表达式
     */
    public static void delete(String path, String pattern) {
        File f = new File(path);
        if (f.exists()) {
            if (f.isFile()) {
                if (Pattern.compile(pattern).matcher(f.getName()).find()) {
                    f.delete();
                }
            } else {
                File[] files = f.listFiles();
                for (File file : files) {
                    delete(file.getPath(), pattern);
                }
            }
        }
    }

    /**
     * 删除文件或目录
     *
     * @param path
     */
    public static void delete(String path) {
        File f = new File(path);
        if (f.exists()) {
            if (f.isFile()) {
                f.delete();
            } else {
                try {
                    org.apache.commons.io.FileUtils.deleteDirectory(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件的扩展名，不带"."
     *
     * @param path
     * @return
     */
    public static String getExtension(String path) {
        return FilenameUtils.getExtension(path);
    }

    /**
     * 创建路径
     *
     * @param path
     */
    public static void createDirectory(String path) {
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();
    }

    public static List<File> getAllFiles(String path) {
        List<File> result = new ArrayList<File>();
        File file = new File(path);
        if (file.isFile()) {
            result.add(file);
        } else if (file.isDirectory()) {
            getAllFiles(file, result);
        }
        return result;
    }

    private static void getAllFiles(File file, List<File> fileLists) {
        File[] files = file.listFiles();
        for (File f : files) {
            fileLists.add(f);
            if (f.isDirectory()) {
                getAllFiles(f, fileLists);
            }
        }
    }

    /**
     * 读取文件所有文本内容
     *
     * @param path
     * @return
     */
    public static String readFileAllText(String path) {
        String result = "";
        File f = new File(path);
        if (f.isFile() && f.canRead()) {
            FileInputStream fs = null;
            try {
                fs = new FileInputStream(f);
                result = org.apache.commons.io.IOUtils.toString(fs, EncodingUtils.DEFAULT_CHARSET);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fs != null) {
                    try {
                        fs.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 读取文件所有行
     *
     * @param path
     * @return
     */
    public static List<String> readFileAllLine(String path) {
        List<String> result = null;
        File f = new File(path);
        if (f.isFile() && f.canRead()) {
            FileInputStream fs = null;
            try {
                fs = new FileInputStream(f);
                result = org.apache.commons.io.IOUtils.readLines(fs, EncodingUtils.DEFAULT_CHARSET);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fs != null) {
                    try {
                        fs.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 将制定内容写入一个文件，默认UTF-8编码
     *
     * @param path
     * @param text
     * @param overwrite
     */
    public static void writeFileAllText(String path, String text, boolean overwrite) {
        File file = new File(path);
        if (file.exists()) {
            if (overwrite) {
                return;
            }
        } else {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdir();
            }
        }

        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(file);
            org.apache.commons.io.IOUtils.write(text, fs, EncodingUtils.DEFAULT_CHARSET);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将制定内容写入一个文件，默认UTF-8编码
     *
     * @param path
     * @param lines
     * @param overwrite
     */
    public static void writeFileAllLine(String path, List<String> lines, boolean overwrite) {
        File file = new File(path);
        boolean exists = file.exists();
        if (file.exists()) {
            if (overwrite) {
                return;
            }
        } else {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdir();
            }
        }

        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(file);
            org.apache.commons.io.IOUtils.writeLines(lines, null, fs, EncodingUtils.DEFAULT_CHARSET);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将制定内容追加到一个文件，如果文件不存在，创建文件
     *
     * @param path
     * @param lines
     */
    public static void appendFile(String path, String... lines) {
        File file = new File(path);
        boolean exists = file.exists();
        if (!exists) {
            String directory = getDirectory(path);
            createDirectory(directory);
        }

        FileOutputStream fs = null;
        try {
            if (!file.exists())
                file.createNewFile();
            fs = new FileOutputStream(file, true);
            org.apache.commons.io.IOUtils.writeLines(Arrays.asList(lines), null, fs, EncodingUtils.DEFAULT_CHARSET);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件
     *
     * @param path
     * @return
     */
    public static byte[] readeFile(String path) {
        byte[] result = null;
        File f = new File(path);
        if (f.isFile() && f.canRead()) {
            FileInputStream stream = null;
            try {
                stream = new FileInputStream(f);
                result = org.apache.commons.io.IOUtils.toByteArray(stream);
            } catch (IOException e) {
                e.printStackTrace();
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 写入文件
     *
     * @param path
     * @param data
     * @param overwrite
     */
    public static void writeFile(String path, byte[] data, boolean overwrite) {
        File file = new File(path);
        if (file.exists()) {
            if (overwrite) {
                return;
            }
        } else {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdir();
            }
        }

        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(file);
            org.apache.commons.io.IOUtils.write(data, fs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
