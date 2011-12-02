package br.com.fourlinux.videostore;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CommentBean {
	private boolean commentFormDisplayed;

	public boolean isCommentFormDisplayed() {
		return commentFormDisplayed;
	}

	public void setCommentFormDisplayed(boolean commentFormDisplayed) {
		this.commentFormDisplayed = commentFormDisplayed;
	}
	
	public void showForm() {
		commentFormDisplayed = true;
	}
	
	public void hideForm() {
		commentFormDisplayed = false;
	}
	
	
}
