/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paivakirja;

import java.util.Scanner;
import ui.UiText;

public class Main {
        public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    UiText ui = new UiText(scanner);
    ui.start();
}
}