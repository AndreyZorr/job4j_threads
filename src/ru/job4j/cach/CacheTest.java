package ru.job4j.cach;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {

    @Test
    public void whenAddFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        var find = cache.findById(base.id());
        assertThat(find.get().name()).isEqualTo("Base");
    }

    @Test
    public void whenAddUpdateFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(new Base(1, "Base updated", 1));
        var find = cache.findById(base.id());
        assertThat(find.get().name()).isEqualTo("Base updated");
    }

    @Test
    public void whenAddDeleteFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.delete(1);
        var find = cache.findById(base.id());
        assertThat(find.isEmpty()).isTrue();
    }

    @Test
    public void whenMultiUpdateThrowException() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        assertThatThrownBy(() -> cache.update(base))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenAdd() {
        var base = new Base(2, "Base2", 2);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        var find = cache.findById(base.id());
        assertThat(find.get().id()).isEqualTo(2);
    }

    @Test
    public void whenAddAndDelete() {
        var base = new Base(3, "Base3", 3);
        var cache = new Cache();
        cache.add(base);
        cache.delete(3);
        var find = cache.findById(base.id());
        assertThat(find.isEmpty()).isTrue();
    }
}