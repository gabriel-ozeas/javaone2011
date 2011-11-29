package br.com.fourlinux.videostore;

import java.math.BigDecimal;

public class MovieStatistics {
	private float rate;
	private long totalVotes;
	private int votedAsFavorite;

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(long totalVotes) {
		this.totalVotes = totalVotes;
	}

	public int getVotedAsFavorite() {
		return votedAsFavorite;
	}

	public void setVotedAsFavorite(int votedAsFavorite) {
		this.votedAsFavorite = votedAsFavorite;
	}
}
