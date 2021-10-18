package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Handler implements RequestStreamHandler {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        try {
            HashMap<String, ArrayList> model = gson.fromJson(reader, HashMap.class);
            if(model == null || model.size() == 0 || !model.containsKey("input")) {
                writer.write(gson.toJson(new Integer[0]));
                logger.log("input is not valid.");
            } else {
                List<Integer> result = new ArrayList<>();
                Utilities.extractIntegersFromNestedObject(model.get("input"), result);
                writer.write(gson.toJson(result));
            }
        } catch (Exception ex) {
            logger.log(ex.toString());
        } finally {
            reader.close();
            writer.close();
        }
    }
}
