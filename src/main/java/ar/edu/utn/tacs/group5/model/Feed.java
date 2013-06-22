package ar.edu.utn.tacs.group5.model;

import java.io.Serializable;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

import ar.edu.utn.tacs.group5.meta.ItemMeta;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private long userId;

    private String title;

    private String link;

    private String description;

    @Attribute(persistent = false)
    private InverseModelListRef<Item, Feed> itemListRef = new InverseModelListRef<Item, Feed>(Item.class,
            ItemMeta.get().feedRef.getName(), this);

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public InverseModelListRef<Item, Feed> getItemListRef() {
        return itemListRef;
    }

    public boolean isValid() {
        return title != null && description != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Feed other = (Feed) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public List<Item> getItems() {
        return getItemListRef().getModelList();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Feed [key=");
        builder.append(key);
        builder.append(", version=");
        builder.append(version);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", title=");
        builder.append(title);
        builder.append(", link=");
        builder.append(link);
        builder.append(", description=");
        builder.append(description);
        builder.append(", items=");
        builder.append(getItems());
        builder.append(", itemListRef=");
        builder.append(itemListRef);
        builder.append("]");
        return builder.toString();
    }

    public static Feed newFeed(long userId, String title, String description) {
        Feed feed = new Feed();
        feed.setUserId(userId);
        feed.setTitle(title);
        feed.setDescription(description);
        return feed;
    }

}
