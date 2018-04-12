package com.recnav.app.Observers;


import com.recnav.app.models.Apps;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;

public class ModelObserver extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        System.out.println("Hello wold");
        return this.updateStates(entity, propertyNames, state, "created");
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        return this.updateStates(entity, propertyNames, currentState, "updated");
    }



    private boolean updateStates(Object entity, String[] propertyNames, Object[] state, String currentProperty){

            for ( int i=0; i<propertyNames.length; i++ ) {
                if ( currentProperty.equals( propertyNames[i] ) ) {
                    state[i] = new Date();
                    return true;
                }
            }
            return false;


    }
}
