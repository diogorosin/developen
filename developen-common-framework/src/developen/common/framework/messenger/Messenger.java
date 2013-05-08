package developen.common.framework.messenger;

import java.awt.Component;

import javax.swing.JOptionPane;

import developen.common.framework.i18n.ErrorTag;
import developen.common.framework.i18n.InformationTag;
import developen.common.framework.i18n.WarningTag;

public final class Messenger {


	public static void show(Exception exception){


		if (!(exception instanceof InvisibleForUser))

			internalMessage(new ErrorTag().toString(), 

					exception.getMessage(),

					JOptionPane.ERROR_MESSAGE);

		exception.printStackTrace();
		

	}


	public static void show(Message message){


		if (message instanceof WarningMessage)

			warn(message);

		else

			if (message instanceof ErrorMessage)

				error(message);

			else

				if (message instanceof InformationMessage)

					info(message);



	}


	private static void warn(Message warn){


		internalMessage(new WarningTag().toString(), 

				warn.getText().toString(), 

				JOptionPane.WARNING_MESSAGE);


	}


	private static void info(Message info){


		internalMessage(new InformationTag().toString(), 

				info.getText().toString(), 

				JOptionPane.INFORMATION_MESSAGE);


	}


	private static void error(Message error){


		internalMessage(new ErrorTag().toString(), 

				error.getText().toString(),

				JOptionPane.ERROR_MESSAGE);


	}


	private static void internalMessage(String title, String message, int type){

		JOptionPane.showMessageDialog(null, message, title, type);

	}


	public static Object ask(Question question){

		return ask(question, null);

	}


	public static Object ask(Question question, Component component){

		return internalAsk(question, component);

	}


	private static Object internalAsk(Question question, Component component){


		return JOptionPane.showOptionDialog(

				component, 

				question.getQuestion(), 

				question.getTitle().toString(),

				JOptionPane.DEFAULT_OPTION,

				JOptionPane.QUESTION_MESSAGE,

				null,

				question.getOptions(),

				question.getSelected()

				);


	}


}