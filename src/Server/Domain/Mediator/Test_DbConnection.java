package Server.Domain.Mediator;

import java.sql.*;

public abstract class Test_DbConnection<T>
{
	Connection conn;

	private final int consistentGets = 0;
	private final int hardParses = 1;
	private final int totalParses = 2;
	private final int sessionCPU = 3;
	private final int parseTimeCPU = 4;
	private final int parseTimeElapsed = 5;
	private final int executeCount = 6;
	private final int recursiveCalls = 7;
	private final int parseCountDescribe = 8;

	public void execute() throws SQLException
	{
		try
		{
			int[] before;
			int[] after;
			long startTime = 0;
			long endTime = 0;

			clearSharedPool();

			before = getStats();
			startTime = System.currentTimeMillis();

			//double execution?
			runCase();

			endTime = System.currentTimeMillis();
			after = getStats();

			report(before, after, startTime, endTime);
		}
		catch (SQLException e)
		{
			throw e;
		}
	}

	abstract ResultSet runCase() throws SQLException;

	/*
	 * Classes for individual questions must implement this method with the specific
	 * code for the question
	 */

	private void report(int[] before, int[] after, long startTime, long endTime)
	{
		System.out.println("Elapsed time (ms)" + (endTime - startTime));
		System.out.println("Consistent gets " + (after[consistentGets] - before[consistentGets]));
		System.out.println("Total parses describe " + (after[parseCountDescribe] - before[parseCountDescribe]));
		System.out.println("Total parses " + (after[totalParses] - before[totalParses]));
		System.out.println("Hard parses " + (after[hardParses] - before[hardParses]));
		System.out.println("Session CPU " + (after[sessionCPU] - before[sessionCPU]));
		System.out.println("Elapsed time parsing  " + (after[parseTimeElapsed] - before[parseTimeElapsed]));
		System.out.println("Parse CPU " + (after[parseTimeCPU] - before[parseTimeCPU]));
		System.out.println("Statement Executions " + (after[executeCount] - before[executeCount]));
		System.out.println(" ");
	}

	private void clearSharedPool() throws SQLException
	{
		Statement stmt = conn.createStatement();
		stmt.execute("alter system flush shared_pool");
		stmt.close();
	}

	private int[] getStats() throws SQLException
	{
		int[] stats = new int[9];

		stats[consistentGets] = getNamedStatistic("consistent gets");
		stats[hardParses] = getNamedStatistic("parse count (hard)");
		stats[totalParses] = getNamedStatistic("parse count (total)");
		stats[sessionCPU] = getNamedStatistic("CPU used by this session");
		stats[parseTimeCPU] = getNamedStatistic("parse time cpu");
		stats[parseTimeElapsed] = getNamedStatistic("parse time elapsed");
		stats[executeCount] = getNamedStatistic("execute count");
		stats[executeCount] = getNamedStatistic("execute count");
		stats[recursiveCalls] = getNamedStatistic("recursive calls");
		stats[parseCountDescribe] = getNamedStatistic("parse count (describe)");

		return stats;
	}

	private int getNamedStatistic(String statName) throws SQLException
	{
		int statValue = 0;
		PreparedStatement stmt = conn.prepareStatement("select value "
				+ " from v$sesstat st  join v$statname nm on (st.statistic# = nm.statistic#)"
				+ " where nm.name = ? " + "  and  st.sid = sys_context('userenv','sid')");
		stmt.setString(1, statName);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		statValue = rs.getInt("value");
		rs.close();
		stmt.close();

		return statValue;
	}
}
