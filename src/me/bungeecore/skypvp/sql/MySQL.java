package me.bungeecore.skypvp.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


public class MySQL {

	private static Connection connection;
	
	private static boolean isConnected() {
		return connection != null;
	}
	
	public static void connect() {
		if (!isConnected()) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Netzwerk?autoReconnect=true", "admin", "trabbi123");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
    private static void Update(String qry) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void registerTables() {
    	Update("CREATE TABLE IF NOT EXISTS Coins (UUID VARCHAR(100), Coins VARCHAR(100))");
    	Update("CREATE TABLE IF NOT EXISTS Crates (UUID VARCHAR(100), NORMAL VARCHAR(100), RANK VARCHAR(100), COINS VARCHAR(100))");
    }
    
    public boolean isPlayerExits(UUID uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Coins WHERE UUID = '" + uuid.toString() + "'");
            ResultSet set = ps.executeQuery();
            boolean get = set.next();
            set.close();
            ps.close();
            return get;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean isPlayerExits2(UUID uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Crates WHERE UUID = '" + uuid.toString() + "'");
            ResultSet set = ps.executeQuery();
            boolean get = set.next();
            set.close();
            ps.close();
            return get;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void registerPlayer(UUID uuid) {
        if (isPlayerExits(uuid)) {
            return;
        }
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Coins (UUID, Coins) VALUES ('" + uuid.toString() + "', '100')");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void registerPlayer2(UUID uuid) {
        if (isPlayerExits2(uuid)) {
            return;
        }
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Crates (UUID, NORMAL, RANK, COINS) VALUES ('" + uuid.toString() + "', '3', '3', '3')");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setCoins(UUID uuid, int coins) {
        Update("UPDATE Coins SET Coins = '" + coins + "' WHERE UUID = '" + uuid.toString() + "'");
    }
    
    public void setNormalCrate(UUID uuid, int crates) {
        Update("UPDATE Crates SET NORMAL = '" + crates + "' WHERE UUID = '" + uuid.toString() + "'");
    }
    
    public void setRankCrate(UUID uuid, int crates) {
        Update("UPDATE Crates SET RANK = '" + crates + "' WHERE UUID = '" + uuid.toString() + "'");
    }
    
    public void setCoinsCrate(UUID uuid, int crates) {
        Update("UPDATE Crates SET COINS = '" + crates + "' WHERE UUID = '" + uuid.toString() + "'");
    }

    public int getCoins(UUID uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Coins WHERE UUID = '" + uuid.toString() + "'");
            ResultSet set = ps.executeQuery();
            set.next();
            int coins = set.getInt("Coins");
            set.close();
            ps.close();
            return coins;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public int getNormalCrates(UUID uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Crates WHERE UUID = '" + uuid.toString() + "'");
            ResultSet set = ps.executeQuery();
            set.next();
            int coins = set.getInt("NORMAL");
            set.close();
            ps.close();
            return coins;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public int getRankCrates(UUID uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Crates WHERE UUID = '" + uuid.toString() + "'");
            ResultSet set = ps.executeQuery();
            set.next();
            int coins = set.getInt("RANK");
            set.close();
            ps.close();
            return coins;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public int getCoinsCrates(UUID uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Crates WHERE UUID = '" + uuid.toString() + "'");
            ResultSet set = ps.executeQuery();
            set.next();
            int coins = set.getInt("COINS");
            set.close();
            ps.close();
            return coins;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public void addCoins(UUID uuid, int coins) {
        setCoins(uuid, (getCoins(uuid) + coins));
    }
    
    public void addNormalCrate(UUID uuid, int crates) {
        setNormalCrate(uuid, (getNormalCrates(uuid) + crates));
    }
    
    public void addRankCrate(UUID uuid, int crates) {
    	setRankCrate(uuid, (getRankCrates(uuid) + crates));
    }
    
    public void addCoinsCrate(UUID uuid, int crates) {
        setCoinsCrate(uuid, (getCoinsCrates(uuid) + crates));
    }

    public void removeCoins(UUID uuid, int coins) {
        setCoins(uuid, (getCoins(uuid) - coins));
    }
    
    public void removeNormalCrate(UUID uuid, int crates) {
        setNormalCrate(uuid, (getNormalCrates(uuid) - crates));
    }
    
    public void removeRankCrate(UUID uuid, int crates) {
    	setRankCrate(uuid, (getRankCrates(uuid) - crates));
    }
    
    public void removeCoinsCrate(UUID uuid, int crates) {
        setCoinsCrate(uuid, (getCoinsCrates(uuid) - crates));
    }
}