package com.suyang.mbg.utils;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.suyang.commons.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class SerializeUtils {
    public static void serializeToFile(String path, Object obj) {
        if (obj == null)
            return;

        String dir = IOUtils.getDirectory(path);
        IOUtils.createDirectory(dir);

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(obj);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T deserializeFromFile(String path) {
        T result = null;
        if (!IOUtils.isFile(path))
            return result;

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(path));
            result = (T) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static byte[] serializeToBytes(Object obj) {
        byte[] result = null;
        if (obj == null)
            return result;

        ByteArrayOutputStream stream = null;
        ObjectOutputStream out = null;
        try {
            stream = new ByteArrayOutputStream();
            out = new ObjectOutputStream(stream);
            out.writeObject(obj);
            result = stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserializeFromBytes(byte[] data) {
        T result = null;
        if (data == null || data.length == 0)
            return result;

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new ByteArrayInputStream(data));
            result = (T) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static void xmlSerializeToFile(String path, Object obj) {
        if (obj == null)
            return;

        String dir = IOUtils.getDirectory(path);
        IOUtils.createDirectory(dir);

        XMLEncoder encoder = null;
        try {
            encoder = new XMLEncoder(new FileOutputStream(path));
            encoder.writeObject(obj);
            encoder.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (encoder != null) {
                encoder.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T xmlDeserializeFromFile(String path) {
        T result = null;
        if (!IOUtils.isFile(path))
            return result;

        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new FileInputStream(path));
            result = (T) decoder.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
        return result;
    }

    public static void xmlSerializeByJAXBToFile(String path, Object obj) {
        if (obj == null)
            return;

        String dir = IOUtils.getDirectory(path);
        IOUtils.createDirectory(dir);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            //格式化输出，即按标签自动换行，否则就是一行输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //设置编码（默认编码就是utf-8）
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //是否省略xml头信息，默认不省略（false）
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
            marshaller.setProperty(CharacterEscapeHandler.class.getName(), (CharacterEscapeHandler) (ch, start, length, isAttVal, writer) -> writer.write(ch, start, length));
            marshaller.marshal(obj, new FileOutputStream(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> T xmlDeserializeByJAXBFromFile(Class<T> clazz, String path) {
        T result = null;
        if (!IOUtils.isFile(path))
            return result;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            result = (T) unmarshaller.unmarshal(new FileInputStream(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
