package developen.common.framework.messenger;

import developen.common.framework.i18n.CancelTag;
import developen.common.framework.i18n.NoTag;
import developen.common.framework.i18n.YesTag;
import developen.common.framework.utils.Tag;


public class Question {
	

	public static final Object[] YES_NO = {new YesTag(), new NoTag()};
	
	public static final Object[] YES_NO_CANCEL = {new YesTag(), new NoTag(), new CancelTag()};
	
	public static final int YES = 0;
	
	public static final int NO = 1;
	
	public static final int CANCEL = 2;
	
	private Tag title;
	
	private Tag question;
	
	private Object[] options;
	
	private Object selected;
	

	public Question(Tag title, Tag question, Object[] options, Object selected){

		
		setTitle(title);
		
		setQuestion(question);
		
		setOptions(options);
		
		setSelected(selected);
		

	}
	

	public void setOptions(Object[] options) {

		this.options = options;

	}

	
	public Object[] getOptions() {

		return options;

	}
	

	public void setSelected(Object selected) {

		this.selected = selected;

	}

	
	public Object getSelected() {

		return selected;

	}

	
	public void setQuestion(Tag question) {

		this.question = question;

	}

	
	public Tag getQuestion() {

		return question;

	}

	
	public void setTitle(Tag title) {

		this.title = title;

	}
	

	public Tag getTitle() {

		return title;

	}
	

}