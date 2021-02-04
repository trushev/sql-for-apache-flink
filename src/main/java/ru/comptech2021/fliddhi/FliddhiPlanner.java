package ru.comptech2021.fliddhi;

import io.siddhi.query.api.SiddhiApp;
import io.siddhi.query.api.execution.query.Query;
import io.siddhi.query.api.execution.query.input.stream.JoinInputStream;
import ru.comptech2021.fliddhi.selector.FliddhiGroupByKeySelector;
import ru.comptech2021.fliddhi.selector.FliddhiJoinKeySelector;
import ru.comptech2021.fliddhi.selector.FliddhiKeySelector;
import ru.comptech2021.fliddhi.selector.FliddhiPlainKeySelector;

public abstract class FliddhiPlanner {

    static FliddhiKeySelector createFliddhiKeySelector(SiddhiApp siddhiApp) {
        Query query = (Query) siddhiApp.getExecutionElementList().get(0);

        System.out.println("FliddhiKeySelectorPlanner");

        if (!query.getSelector().getGroupByList().isEmpty()) {
            return new FliddhiGroupByKeySelector(siddhiApp);
        }
        if (query.getInputStream() instanceof JoinInputStream) {
            return new FliddhiJoinKeySelector(siddhiApp);
        }
        //todo if parallelism = 1:
        return new FliddhiPlainKeySelector(siddhiApp);

    }
}