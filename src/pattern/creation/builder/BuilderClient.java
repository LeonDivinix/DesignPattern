package pattern.creation.builder;

public class BuilderClient {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder(); // 创建生成器
        Direct direct = new Direct(builder);
        direct.construct();
        Product product = builder.getResult();
        System.out.println(product.toString());
    }
}

class Direct {
    private Builder builder;

    public Direct(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
        builder.buildPart3();
    }
}

class Product {
    private String part1;
    private String part2;
    private String part3;

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }

    @Override
    public String toString() {
        return this.part1  + this.part2 + this.part3;
    }
}

interface Builder {
    void buildPart1();
    void buildPart2();
    void buildPart3();
    Product getResult();
}

class ConcreteBuilder implements Builder {
    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("<html>\n\t<head>home</head>\n");
    }

    @Override
    public void buildPart2() {
        product.setPart2("\t<body>\n\t\tHello World!\n\t</body>\n");

    }

    @Override
    public void buildPart3() {
        product.setPart3("</html>");
    }

    public Product getResult() {
        return product;
    }
}