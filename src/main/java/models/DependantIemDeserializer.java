//package models;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.type.CollectionType;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//public class CustomDeserializer extends JsonDeserializer<DependantItem> {
//
//    @Override
//    public Item deserialize(JsonParser parser, DeserializationContext context) throws IOException {
//        final JsonNode itemNode = parser.getCodec().readTree(parser);
//        final ObjectMapper mapper = (ObjectMapper)parser.getCodec();
//        return new Item(refsFor(itemNode), thingsFor(itemNode, mapper));
//    }
//
//    private List<String> refsFor(JsonNode rootNode) {
//        final JsonNode refNode = rootNode.get("ref");
//        if (nodeTypeFor(refNode) == null) {
//            return null;
//        } else if (nodeTypeFor(refNode).equals("STRING")) {
//            return refsForString(refNode);
//        } else if (nodeTypeFor(refNode).equals("ARRAY")) {
//            return refsForArray(refNode);
//        } else {
//            throw new Exception("Could not deserialize ref with node type " + nodeTypeFor(refNode));
//        }
//    }
//
//    private String nodeTypeFor(JsonNode node) {
//        return node == null ? null : node.getNodeType().name();
//    }
//
//    private List<String> refsForString(JsonNode refNode) {
//        return Collections.singletonList(refNode.textValue());
//    }
//
//    private List<String> refsForArray(JsonNode refNode) {
//        List<String> result = new ArrayList<>();
//        Iterator<JsonNode> refIterator = refNode.elements();
//        while (refIterator.hasNext()) {
//            JsonNode ref = refIterator.next();
//            result.add(ref.textValue());
//        }
//        return result;
//    }
//
//    private List<Thing> thingsFor(JsonNode rootNode, ObjectMapper mapper) throws IOException {
//        ArrayNode thingsNode = ((ArrayNode) rootNode.get("ithings"));
//        CollectionType thingType = mapper.getTypeFactory().constructCollectionType(List.class, Thing.class);
//        return mapper.readValue(thingsNode.toString(), thingType);
//    }
//
//}
