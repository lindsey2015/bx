package cn.edu.xmut.functional;

import org.eclipse.jetty.server.Server;

/**
 * 浣跨敤Jetty杩愯璋冭瘯Web搴旂敤, 鍦–onsole杈撳叆鍥炶溅蹇�閲嶆柊鍔犺浇搴旂敤.
 * 
 * @author calvin
 */
public class QuickStartServer {

	public static final int PORT = 80;
	public static final String CONTEXT = "/bx";
	public static final String BASE_URL = "http://localhost:"+PORT+CONTEXT;
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc" };

	public static void main(String[] args) throws Exception {
		// 璁惧畾Spring鐨刾rofile 涓哄紑鍙戠幆澧�
		System.setProperty("spring.profiles.active", "development");

		// 鍚姩Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			server.start();

			System.out.println("Server running at " + BASE_URL);
			System.out.println("Hit Enter to reload the application");

			// 绛夊緟鐢ㄦ埛杈撳叆鍥炶溅閲嶈浇搴旂敤.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
