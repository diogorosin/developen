package developen.common.framework.messenger;

import developen.common.framework.i18n.QuestionTag;
import developen.common.framework.utils.Tag;

public class SimplifiedQuestion extends Question {

	
	public SimplifiedQuestion(Tag question){
		
		super(new QuestionTag(), question, Question.YES_NO, Question.YES_NO[1]);
		
	}
	

}