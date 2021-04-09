package codechef;

import java.util.ArrayList;
import java.util.List;

public class TheleadGame {

	public static void main(String[] args) {
		int player1[] = { 140, 89, 90, 112, 88 };
		int player2[] = { 82, 134, 110, 106, 90 };
		List<Integer> cumulativePlayer1 = new ArrayList<Integer>();
		List<Integer> cumulativePlayer2 = new ArrayList<Integer>();
		int temp1 = 0;
		int temp2 = 0;
		for (int i = 0; i < player1.length; i++) {
			temp1 = temp1 + player1[i];
			cumulativePlayer1.add(temp1);
			temp2 = temp2 + player2[i];
			cumulativePlayer2.add(temp2);
		}
		List<Integer> winOfPlayers = new ArrayList<Integer>();
		List<Integer> leadOfPlayers = new ArrayList<Integer>();
		for (int j = 0; j < cumulativePlayer2.size(); j++) {
			if (cumulativePlayer1.get(j) > cumulativePlayer2.get(j)) {
				int lead = cumulativePlayer1.get(j) - cumulativePlayer2.get(j);
				winOfPlayers.add(1);
				leadOfPlayers.add(lead);
			} else {
				int lead1 = cumulativePlayer2.get(j) - cumulativePlayer1.get(j);
				winOfPlayers.add(2);
				leadOfPlayers.add(lead1);
			}
		}
		int leadWin = leadOfPlayers.get(0);
		for (int k = 0; k < leadOfPlayers.size(); k++) {
			if (leadOfPlayers.get(k) > leadWin) {
				leadWin = leadOfPlayers.get(k);
			}
		}
		int playerWin = 0;
		for (int l = 0; l < leadOfPlayers.size(); l++) {
			if (leadOfPlayers.get(l) == leadWin) {
				playerWin = winOfPlayers.get(l);
			}
		}
		System.out.println(playerWin);
		System.out.println(leadWin);

	}

}
