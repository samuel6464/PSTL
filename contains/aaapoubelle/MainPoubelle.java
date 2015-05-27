package aaapoubelle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class MainPoubelle {



		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Gson gson = new Gson() ;
			
			 
			try {
		 
				//BufferedReader br = new BufferedReader(
					//new FileReader("/Users/samuelbell-bell/Desktop/PSTL/roi.json"));
				//br.
				//convert the json string back to object
				Bases obj = gson.fromJson(new FileReader("/Users/samuelbell-bell/Desktop/PSTL/roi.json"), Bases.class);
				 System.out.println(gson.toJson(obj));
				 
				//System.out.println(obj);
				
			//	System.out.println(obj.toString());
		 
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
