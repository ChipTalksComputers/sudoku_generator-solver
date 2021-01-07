package application.custom;
import application.GenerateController;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

public class timer extends AnimationTimer{
		
		Label timeLabel;
		public timer(Label timeLabel){
			this.timeLabel = timeLabel;
		}

		private long timestamp;
		private long time = 0;
		private long fraction = 0;
		@Override
		public void start() {
			timestamp = System.currentTimeMillis() - fraction;
			super.start();
		}
		@Override
		public void stop() {
			super.stop();
			fraction = System.currentTimeMillis() - timestamp;
		}
		@Override
		public void handle(long now) {
			long newTime = System.currentTimeMillis();
			if(timestamp + 1000 <= newTime) {
				long deltaT = (newTime - timestamp)/1000;
				time += deltaT;
				timestamp += 1000*deltaT;
				this.timeLabel.setText(Long.toString(time));
				//System.out.println(time);
			}
		}
		
		public long getTime() {
			return time;
		}
}
