package pattern.creation.builder;

public class SimpleBuilderClient {

    public static void main(String[] args) {
        SimpleArticleBuilder builder = new CslArticleBuilder();
        Article article = builder.buildBanner().buildContent().buildTitle().build();
        System.out.println(article);
    }
}


class Article {
    private String banner;
    private String title;
    private String content;

    void setBanner(String banner) {
        this.banner = banner;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "banner='" + banner + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

interface SimpleArticleBuilder {
    SimpleArticleBuilder buildBanner();
    SimpleArticleBuilder buildTitle();
    SimpleArticleBuilder buildContent();
    Article build();
}

class CslArticleBuilder implements SimpleArticleBuilder {
    private String banner;
    private String title;
    private String content;
    @Override
    public SimpleArticleBuilder buildBanner() {
        this.banner = "<img src=\"http://127.0.0.1/img/2.jpg\" />";
        return this;
    }

    @Override
    public SimpleArticleBuilder buildTitle() {
        this.title = "<title>CSL</title>";
        return this;
    }

    @Override
    public SimpleArticleBuilder buildContent() {
        this.content = "<div>this is CSL test</div>";
        return this;
    }

    @Override
    public Article build() {
        Article a = new Article();
        a.setBanner(this.banner);
        a.setTitle(this.title);
        a.setContent(this.content);
        return a;
    }
}