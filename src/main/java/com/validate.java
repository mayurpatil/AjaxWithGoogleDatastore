package com;

import java.io.IOException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class validate extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/html");
		String USN = req.getParameter("USN");
		String Name = null;

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Key dbKey = KeyFactory.createKey("Student_DB", USN);
		Query q = new Query("Student_DB", dbKey);
		PreparedQuery pq = datastore.prepare(q);

		try {
			if (pq != null) {
				Entity result = pq.asSingleEntity();
				USN = result.getProperty("USN").toString();
				Name = result.getProperty("Name").toString();
				resp.getWriter().println(
						"<center><H1> Record Found - USN:" + USN + " Name: "
								+ Name + "</H1></center>");
				
				
				
			}
		} catch (Exception e) {
			resp.getWriter().println(
					"<center><H1> Record NOT Found - USN:" + USN
							+ "</H1></center>");
		}
	}
}
