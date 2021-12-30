package Model.Expressions;

import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.Value;

public interface Expression {
    Value eval(MyIDictionary<String, Value> table, MyIDictionary<Integer, Value> heap) throws Exception;

    Type typeCheck(MyIDictionary<String, Type> typeEnvironment) throws Exception;
}
