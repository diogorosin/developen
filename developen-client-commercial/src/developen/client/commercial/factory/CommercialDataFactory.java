package developen.client.commercial.factory;

import java.util.ArrayList;
import java.util.List;

import developen.client.commercial.widget.DBMeasureUnitComboBoxType;
import developen.common.commercial.mvc.MeasureUnit;
import developen.common.framework.messenger.Messenger;
import developen.common.persistence.dpa.DPA;
import developen.common.persistence.query.Column;
import developen.common.persistence.query.ColumnQuery;
import developen.common.persistence.query.In;
import developen.common.persistence.session.Session;

public class CommercialDataFactory {


	public static List<Object> getMeasureUnitListByType(DBMeasureUnitComboBoxType[] types){


		List<Object> list = new ArrayList<Object>();

		try {

			Session session = DPA.getSessionFactory().createSession();

			ColumnQuery q = new ColumnQuery(MeasureUnit.class);

			List<Long> in = new ArrayList<Long>();

			for (DBMeasureUnitComboBoxType type : types) {

				switch (type) {

				case SIZE: in.add(new Long(1));

				break;

				case MASS: in.add(new Long(2));

				break;

				case VOLUME: in.add(new Long(3));

				break;

				case TIME: in.add(new Long(4));

				break;

				case UNIT: in.add(new Long(5));

				break;

				}

			}

			if (in.size() > 0)

				q.add(new In(new Column("group", MeasureUnit.class), in.toArray()));

			list = session.list(q);

			session.close();

		} catch (Exception e) {

			Messenger.show(e);

		}

		return list;


	}


}