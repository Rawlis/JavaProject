package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class THSimulator extends Thread {
	private float temp;
	private float humi;
	private float heaterOnTemp = 10;
	private float airconOnTemp = 20;
	Random random = new Random();
	
	THSimulator(float temp, float humi){
		this.temp=temp;
		this.humi=humi;
	}
	@Override
	public void run() {
		try {
			//1초마다 
			if(temp<heaterOnTemp)
				tempUp();
			if(temp>airconOnTemp)
				tempDown();
			normallyUpdate();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//평상시
	public void normallyUpdate()
	{
		if(random.nextInt(2)==0) {
			temp+=random.nextFloat()/4;
			humi+=random.nextFloat()/4;
		}
		else {
			temp-=random.nextFloat()/4;
			humi-=random.nextFloat()/4;
		}
	}
	//에어컨
	public void tempDown()
	{
		temp-=random.nextFloat()/2;
	}
	//히터
	public void tempUp()
	{
		temp+=random.nextFloat()/2;
	}
	
	public float getTemp() {
		return temp;
	}
	public float getHumi() {
		return humi;
	}
}