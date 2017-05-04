package util;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class CommonUtil {
	
	public static final String ERROR_CODE = "error_code";
	
	//�û��������ڻ��������
	public static final String ERROR_CODE_NOT_EXIST_USER = "1530";
	//����ͷû��userid������
	public static final String ERROR_CODE_NO_USERID_ON_REQUEST_HEAD = "1531";
	//���û����Ѿ���ע�����
	public static final String ERROR_CODE_USERNAME_BEEN_REGISTERED = "1532";
	//û�е�¼������Ҫ���µ�¼
	public static final String ERROR_CODE_UNLOGIN = "1533";
	//������������ȱʧ
	public static final String ERROR_CODE_REQUEST_PARAMETER_ERROR = "1534";
	//��ǰ��Ʒ�Ѿ���ӹ��ղ�
	public static final String ERROR_CODE_HAD_ADD_FAVORITES = "1535";
	//��Ʒ�Ѿ�����ղ�ʧ��
	public static final String ERROR_CODE_FAILER_ADD_FAVORITES = "1536";
	
	//ȡ������ʧ��
    public static final String ERROR_CODE_FAILER_CANCLE_ORDER = "1537";
    
    //û�иö�������
    public static final String ERROR_CODE_NO_ORDER_DETAIL = "1538";
	
	
	// header ��������//
	private static final String ENCODING_PREFIX = "encoding";
	private static final String NOCACHE_PREFIX = "no-cache";
	private static final String ENCODING_DEFAULT = "UTF-8"; //
	private static final boolean NOCACHE_DEFAULT = true;

	// content-type ���� //
	private static final String TEXT = "text/plain";
	private static final String JSON_STR = "application/json";
	private static final String XML = "text/xml";
	private static final String HTML = "text/html";
	
	
	public static String formatDate(String time) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return format.format(time);
	}

	// �ƹ�jsp/freemakerֱ������ı��ĺ��� //

	/**
	 * ֱ��������ݵļ�㺯��.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
	 * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
	 * "no-cache:false");
	 * 
	 * @param headers
	 *            �ɱ��header���飬Ŀǰ���ܵ�ֵΪ"encoding:"��"no-cache:",Ĭ��ֵ�ֱ�ΪUTF-8��true.
	 */
	private static void render(final HttpServletResponse response,
			final String contentType, final String content,
			final String... headers) {
		try {
			// ����headers����
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName,
						NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName
							+ "����һ���Ϸ���header����");
			}

			// ����headers����
			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			PrintWriter writer = response.getWriter();
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ֱ������ı�.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final HttpServletResponse response,
			final String text, final String... headers) {
		render(response, TEXT, text, headers);
	}

	/**
	 * ֱ�����HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final HttpServletResponse response,
			final String html, final String... headers) {
		render(response, HTML, html, headers);
	}

	/**
	 * ֱ�����XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final HttpServletResponse response,
			final String xml, final String... headers) {
		render(response, XML, xml, headers);
	}


	/**
	 * ֱ�����JSON.
	 * 
	 * @param object
	 *            object����,����ת��Ϊjson�ַ���.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final HttpServletResponse response,
			Object data, final String... headers) {
		//SerializerFeature.DisableCircularReferenceDetect:����ѭ�����ü��
		String jsonString = JSON.toJSONString(data,
				SerializerFeature.DisableCircularReferenceDetect);
		render(response, JSON_STR, jsonString, headers);
	}

	public static void renderJsonWithFilter(final HttpServletResponse response,
			Object data, SerializeFilter filter,final String... headers) {
		String jsonString = JSON.toJSONString(data, filter,
				SerializerFeature.DisableCircularReferenceDetect);
		render(response, JSON_STR, jsonString, headers);
	}
	
	/**
	 * ֱ�����������ת��
	 * 
	 * @param rep
	 * @param message
	 * @param URL
	 * @param headers
	 */
	public static void renderScript(final HttpServletResponse rep,
			final String message, final String... headers) {
		PrintWriter printer = null;
		try {
			rep.setContentType("text/html;charset=UTF-8");
			printer = rep.getWriter();
			printer.write("<script language = 'javascript'>");
			printer.write("alert('" + message + "'),");
			printer.write("window.history.go(-1)");
			printer.write("</script>");
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		} finally {
			if (printer != null) {
				printer.close();
			}
		}
	}

}