package me.workwolf.betterannouncement.Database;

import me.workwolf.betterannouncement.BetterAnnouncement;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database {

    public Connection connection;

    private final BetterAnnouncement betterAnnouncement;


    public Database(BetterAnnouncement betterAnnouncement, String file) throws ClassNotFoundException, SQLException {
        this.betterAnnouncement = betterAnnouncement;
        this.connection = DriverManager.getConnection("jdbc:sqlite:"+file);
        Class.forName("org.sqlite.JDBC");
        createTableAnnounce();
    }

    private void createTableAnnounce() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS announce(playerid TEXT NOT NULL, millisec NUMERIC)";
        Statement st = this.connection.createStatement();
        st.execute(sql);
    }

    public int addPlayer(String playerID, long millisec) {
        String sql = "INSERT INTO announce(playerid, millisec) VALUES (?, ?)";
        int numRowsInserted = 0;
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(sql);
            ps.setString(1, playerID);
            ps.setLong(2, millisec);
            numRowsInserted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
        }
        return numRowsInserted;
    }

    public Long getMs(UUID uuid) {
        String sql = "SELECT millisec FROM announce WHERE playerid = ?";
        try {
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.setString(1, uuid.toString());
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getLong("millisec");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0L;
    }

    public int updateMs(UUID uuid, long Score) throws SQLException {
        String sql = "UPDATE announce set millisec = ? WHERE playerid = ?";
        int numRowsInserted = 0;
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(sql);
            ps.setLong(1, Score);
            ps.setString(2, uuid.toString());
            numRowsInserted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
        }
        return numRowsInserted;
    }

    public List<String> getAllPlayers() {
        String sql = "SELECT playerid FROM announce";
        List<String> uuid = new ArrayList<>();
        try {
            PreparedStatement st = this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                uuid.add(rs.getString("playerid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return uuid;
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
