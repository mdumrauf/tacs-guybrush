package ar.edu.utn.tacs.group5.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slim3.datastore.ModelMeta;
import org.slim3.tester.AppEngineTestCase;

import ar.edu.utn.tacs.group5.meta.FeedMeta;
import ar.edu.utn.tacs.group5.meta.ItemMeta;

public class ItemTest extends AppEngineTestCase {

	@Test
	public void testIsValid() throws Exception {
		Item item = new Item();
		assertFalse(item.isValid());
		item = getParsedItem(ItemMeta.get());
		assertFalse(item.isValid());
		Feed feed = getParsedItem(FeedMeta.get());
		item.setFeed(feed);
		assertTrue(item.isValid());
	}

	private static <T, M extends ModelMeta<T>> T getParsedItem(M modelMeta) {
		String modelJson = "{ title: \"foo\", description: \"bar\", link: \"http://www.foo.com\" }";
		return modelMeta.jsonToModel(modelJson);
	}
}
