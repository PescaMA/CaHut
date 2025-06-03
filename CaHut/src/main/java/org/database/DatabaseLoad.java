package org.database;

import java.util.ArrayList;
import java.util.Optional;

public interface DatabaseLoad <T extends DatabaseClass> extends DatabaseClass {
    T makeNew();

    default ArrayList<T> loadAll (){
        ArrayList<T> result = new ArrayList<>();
        Optional<ArrayList<Long>> optPks = Database.getPKs( tableName() ); // presumed primary keys
        if(optPks.isEmpty()) return result;
        ArrayList<Long> pks = optPks.get();

        for(Long id: pks){
            T newEl = makeNew();
            newEl.load(id);
            result.add(newEl);
        }
        return result;
    }
}
