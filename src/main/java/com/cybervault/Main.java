package com.cybervault;

import com.cybervault.data.DataBaseManager;
import com.cybervault.model.VaultItem;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Cyber Vault Starting...");

        DataBaseManager db = new DataBaseManager();

        db.createNewDatabase();
        System.out.println("Data Base is tested. Active");

        db.addCategory("Social Media");

        System.out.println("\n--- Data Test ---");

        db.addVaultItem("LOGIN", "Twitter/X", "markplier", "password_xyz", null, "https://x.com", 1);
        db.addVaultItem("NOTE", "MEXT Scholarship Notes", null, null, "Documents for Japan Scholarship...", "https://mext.go.jp", 1);

        System.out.println("Successfully write data to disc.");

        System.out.println("\n--- Reread disc data  ---");

        List<VaultItem> everything = db.getAllItems();

        for (VaultItem item : everything) {
            System.out.print("Found item: " + item.getAppName() + " | Type: " + item.getClass().getSimpleName() + " -> ");
            item.displaySummary();
        }

        System.out.println("\n=========================================");
    }
}