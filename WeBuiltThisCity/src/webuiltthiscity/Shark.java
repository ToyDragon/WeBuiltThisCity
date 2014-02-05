package webuiltthiscity;

public class Shark {
	int x,y,w,h;
	public void tick(){
		x -= GameMain.block_speed;
	}
	public double dist(Shark b){
		int[][] ps = new int[][]{
				{x,y},
				{x+w,y},
				{x+w,y+h},
				{x,y+h}
		};
		int[][] bs = new int[][]{
				{b.x,b.y},
				{b.x+b.w,b.y},
				{b.x+b.w,b.y+b.h},
				{b.x,b.y+b.h}
		};
		double min = Double.MAX_VALUE;
		
		//distance between corners
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				double dist = Math.sqrt((ps[i][0] - bs[j][0]) * (ps[i][0] - bs[j][0]) + (ps[i][1] - bs[j][1]) * (ps[i][1] - bs[j][1]));
				min = dist < min ? dist : min;
			}
		}
		
		//any corners in box
		for(int i = 0; i < 4; i++){
			if(ps[i][0] >= b.x && ps[i][0] <= b.x+b.w && ps[i][1] >= b.y && ps[i][1] <= b.y + b.h){
				min = 0;
			}
			if(bs[i][0] >= x && bs[i][0] <= x+w && bs[i][1] >= y && bs[i][1] <= y + h){
				min = 0;
			}
		}
		
		return min;
	}
}
