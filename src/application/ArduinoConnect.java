package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fazecast.jSerialComm.SerialPort;
import java.util.LinkedList;
import java.util.Queue;

public class ArduinoConnect extends Thread {
	private int temp;
	private int humi;
	private int count;
	private BufferedReader input;
	SerialPort arduinoPort = null;
	String receivedData;
	Queue<String> bufferQueue = new LinkedList<>();

	ArduinoConnect() {
		arduinoPort = SerialPort.getCommPort("COM3");
		arduinoPort.setComPortParameters(9600, 8, 1, 0);
		arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 2000, 0);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 시리얼 포트 열기
		while (true) {
			if (!arduinoPort.openPort()) {
				System.err.println("Failed to open the Arduino port.");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.err.println("Connect");
				break;
			}
		}
		try {
			// 입력 스트림 생성
			input = new BufferedReader(new InputStreamReader(arduinoPort.getInputStream()));

			// 데이터 수신 및 출력
			while (true) {
				try {
					// 아두이노로부터 데이터 읽기 (블로킹)
					receivedData = input.readLine();

					if (receivedData != null) {
						// 잘 받았으면 데이터 처리
						// 수신 데이터에서 숫자 추출
						/*
						 * String regex = "\\d+"; // 숫자에 매칭되는 정규 표현식 Pattern pattern =
						 * Pattern.compile(regex); Matcher matcher = pattern.matcher(receivedData);
						 * count = 0; while (matcher.find()) { String number = matcher.group(); if
						 * (count == 0) temp = Integer.parseInt(number); else if (count == 2) humi =
						 * Integer.parseInt(number); count++; }
						 */
						System.out.println("test1");
						bufferQueue.add(receivedData);
					}
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		} finally {
			// 시리얼 포트 닫기
			try {
				input.close();
				arduinoPort.closePort();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String getRec() {
		return bufferQueue.poll();
	}

	public int getTemp() {
		return temp;
	}

	public int getHumi() {
		return humi;
	}

	public void sendMessage(String s) {
		arduinoPort.writeBytes(s.getBytes(), s.length());
	}
}
