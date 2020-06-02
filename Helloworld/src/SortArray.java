import java.util.Arrays;

public class SortArray {
	public static void sortAscending(int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
				i = -1;
			}
		}
		for (int j : arr) {
			System.out.print(j);
		}
		System.out.println();
	}

	public static void sortDescending(int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] < arr[i + 1]) {
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
				i = -1;
			}
		}
		for (int j : arr) {
			System.out.print(j);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 5, 1, 3, 6, 8, 2, 9, 0, 10 };
		sortAscending(arr);
		sortDescending(arr);
	}
}
