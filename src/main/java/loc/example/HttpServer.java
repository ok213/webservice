package loc.example;

import loc.example.service.UserService;
import loc.example.service.UserServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) throws Throwable {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket s = ss.accept();
            System.err.println("Client accepted");
            new Thread(new SocketProcessor(s)).start();
        }
    }

    private static class SocketProcessor implements Runnable {

        private Socket socket;
        private InputStream is;
        private OutputStream os;
        private UserService userService;

        private SocketProcessor(Socket socket) throws Throwable {
            this.socket = socket;
            this.is = socket.getInputStream();
            this.os = socket.getOutputStream();
            this.userService = new UserServiceImpl();
        }

        public void run() {
            try {
                writeResponse(userService.getResponse(readInputHeaders()));
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            System.err.println("Client processing finished");
        }

        private void writeResponse(String s) throws Throwable {
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP/1.1 200 OK\r\n");
            sb.append("Server: CoolServer/2020-02-01\r\n");
            sb.append("Content-Type: text/html\r\n");
            sb.append("Content-Length: ");
            sb.append(s.length());
            sb.append("\r\n");
            sb.append("Connection: close\r\n\r\n");
            sb.append(s);
            os.write(sb.toString().getBytes());
            os.flush();
        }

        private String readInputHeaders() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            //code to read headers
            String headerLine;
            while ((headerLine = br.readLine()).length() != 0) {
//                System.out.println(headerLine);
            }

            //code to read the post data
            StringBuilder request = new StringBuilder();
            while (br.ready()) {
                request.append((char) br.read());
            }
            return request.toString();
        }
    }

}
