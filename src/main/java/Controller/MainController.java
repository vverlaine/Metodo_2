package Controller;

import Model.Producto;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    static String Variable = "a";
    private static List<Producto> producto = new ArrayList<>();
    private static double a;

    public static void main(String[] args) {

        producto.add(new Producto("Agua", 35));
        producto.add(new Producto("Jabon", 89));
        producto.add(new Producto("Detergente", 10));
        producto.add(new Producto("Prensa", 67));
        producto.add(new Producto("Carro", 55));

        Observable<Producto> obs = Observable.from(producto);

        obs.filter(new Func1<Producto, Boolean>() {
            @Override
            public Boolean call(Producto producto) {
                return producto.getNombre().contains(Variable);
            }
        })
                .subscribe(new Subscriber<Producto>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("COMPLETADO PRIMER FILTRO");
                        System.out.println();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("ERROR PRIMER FILTRO " + throwable);
                    }

                    @Override
                    public void onNext(Producto producto) {
                        System.out.println(producto.getNombre() + " " + producto.getPrecio());
                    }
                });

        obs.filter(new Func1<Producto, Boolean>() {
            @Override
            public Boolean call(Producto producto) {
                return producto.getNombre().contains(Variable);
            }
        }).map((result) -> {
            Producto p = result;
            return p.getPrecio();
        }).reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer int1, Integer int2) {
                return int1 + int2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("COMPLETADO SEGUNDO FILTRO !!!");
                System.out.println();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("ERROR SEGUNDO FILTRO " + throwable);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("LA SUMA DE LOS PRODUCTOS ES: " + integer);
            }
        });

        obs.filter(new Func1<Producto, Boolean>() {
            @Override
            public Boolean call(Producto producto) {
                return producto.getNombre().contains(Variable);
            }
        }).map((result) -> {
            Producto p = result;
            return p.getPrecio();
        }).reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return Math.max(integer, integer2);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("COMPLETADO TERCER FILTRO !!!");
                System.out.println();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("ERROR EN EL TERCER FILTRO " + throwable);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("EL PRECIO MAXIMO ES: " + integer);
            }
        });

        obs.filter(new Func1<Producto, Boolean>() {
            @Override
            public Boolean call(Producto producto) {
                return producto.getNombre().contains(Variable);
            }
        }).subscribe(new Subscriber<Producto>() {
            @Override
            public void onCompleted() {
                System.out.println("COMPLETADO CUARTO FILTRO !!!");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("ERROR EN CUARTO FILTRO " + throwable);
            }

            @Override
            public void onNext(Producto producto) {
                List<Integer> promedio = new ArrayList<>();
                promedio.add(producto.getPrecio());
                for (int i = 0; i < promedio.size(); i++) {
                    a += Double.parseDouble(promedio.get(i).toString());
                }
                System.out.println("EL PROMEDIO ES: " + a / promedio.size());
            }
        });

    }
}
