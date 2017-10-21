package facebook;

import java.util.*;

/**
给你一个机器人和一个房间，你并不知道机器人在房间什么位置，你也不知道房间的形状大小，你有一个遥控器，可以让机器人走前后左右四个方向。
这里给你一个move method : boolean move(int direction), direction: 0,1,2,3 表示四个方向。能移动就返回true,不能移动表示false。
问你：这个房间多大。
 */
public class RobotMove {
	private Random rand = new Random();
	
	public int getArea() {
		int res = 1;
		int[] pos = new int[]{0,0};
		Set<String> used = new HashSet<>();
		used.add(toHash(pos));
		for (int i = 0; i < 4; i++) {
			res += helper(pos, i, used);
		}
		return res;
	}

	private int helper(int[] pos, int direction, Set<String> used) {
		switch(direction) {
		case 0:
			pos[0]++;
			break;
		case 1:
			pos[0]--;
			break;
		case 2:
			pos[1]++;
			break;
		case 3:
			pos[1]--;
			break;
		default:
			throw new RuntimeException();
		}
		String key = toHash(pos);
		if(used.contains(key) || !move(direction)) return 0;
		int res = 1;
		used.add(key);
		for (int i = 0; i < 4; i++) {
			res += helper(pos, i, used);
		}
		move(-direction);
		return res;
	}

	private  String toHash(int[] pos) {
		return pos[0]+","+pos[1];
	}

	private boolean move(int direction) {
		if (rand.nextInt(1) == 0) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
