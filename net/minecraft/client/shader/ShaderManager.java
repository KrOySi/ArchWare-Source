/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.client.shader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderDefault;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.client.shader.ShaderLoader;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ShaderDefault DEFAULT_SHADER_UNIFORM = new ShaderDefault();
    private static ShaderManager staticShaderManager;
    private static int currentProgram;
    private static boolean lastCull;
    private final Map<String, Object> shaderSamplers = Maps.newHashMap();
    private final List<String> samplerNames = Lists.newArrayList();
    private final List<Integer> shaderSamplerLocations = Lists.newArrayList();
    private final List<ShaderUniform> shaderUniforms = Lists.newArrayList();
    private final List<Integer> shaderUniformLocations = Lists.newArrayList();
    private final Map<String, ShaderUniform> mappedShaderUniforms = Maps.newHashMap();
    private final int program;
    private final String programFilename;
    private final boolean useFaceCulling;
    private boolean isDirty;
    private final JsonBlendingMode blendingMode;
    private final List<Integer> attribLocations;
    private final List<String> attributes;
    private final ShaderLoader vertexShaderLoader;
    private final ShaderLoader fragmentShaderLoader;

    public ShaderManager(IResourceManager resourceManager, String programName) throws JsonException, IOException {
        JsonParser jsonparser = new JsonParser();
        ResourceLocation resourcelocation = new ResourceLocation("shaders/program/" + programName + ".json");
        this.programFilename = programName;
        IResource iresource = null;
        try {
            JsonArray jsonarray2;
            JsonArray jsonarray1;
            iresource = resourceManager.getResource(resourcelocation);
            JsonObject jsonobject = jsonparser.parse(IOUtils.toString((InputStream)iresource.getInputStream(), (Charset)StandardCharsets.UTF_8)).getAsJsonObject();
            String s = JsonUtils.getString(jsonobject, "vertex");
            String s1 = JsonUtils.getString(jsonobject, "fragment");
            JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "samplers", null);
            if (jsonarray != null) {
                int i = 0;
                for (Object jsonelement : jsonarray) {
                    try {
                        this.parseSampler((JsonElement)jsonelement);
                    }
                    catch (Exception exception2) {
                        JsonException jsonexception1 = JsonException.forException(exception2);
                        jsonexception1.prependJsonKey("samplers[" + i + "]");
                        throw jsonexception1;
                    }
                    ++i;
                }
            }
            if ((jsonarray1 = JsonUtils.getJsonArray(jsonobject, "attributes", null)) != null) {
                int j = 0;
                this.attribLocations = Lists.newArrayListWithCapacity((int)jsonarray1.size());
                this.attributes = Lists.newArrayListWithCapacity((int)jsonarray1.size());
                for (Iterator jsonelement1 : jsonarray1) {
                    try {
                        this.attributes.add(JsonUtils.getString((JsonElement)jsonelement1, "attribute"));
                    }
                    catch (Exception exception1) {
                        JsonException jsonexception2 = JsonException.forException(exception1);
                        jsonexception2.prependJsonKey("attributes[" + j + "]");
                        throw jsonexception2;
                    }
                    ++j;
                }
            } else {
                this.attribLocations = null;
                this.attributes = null;
            }
            if ((jsonarray2 = JsonUtils.getJsonArray(jsonobject, "uniforms", null)) != null) {
                int k = 0;
                for (JsonElement jsonelement2 : jsonarray2) {
                    try {
                        this.parseUniform(jsonelement2);
                    }
                    catch (Exception exception) {
                        JsonException jsonexception3 = JsonException.forException(exception);
                        jsonexception3.prependJsonKey("uniforms[" + k + "]");
                        throw jsonexception3;
                    }
                    ++k;
                }
            }
            this.blendingMode = JsonBlendingMode.parseBlendNode(JsonUtils.getJsonObject(jsonobject, "blend", null));
            this.useFaceCulling = JsonUtils.getBoolean(jsonobject, "cull", true);
            this.vertexShaderLoader = ShaderLoader.loadShader(resourceManager, ShaderLoader.ShaderType.VERTEX, s);
            this.fragmentShaderLoader = ShaderLoader.loadShader(resourceManager, ShaderLoader.ShaderType.FRAGMENT, s1);
            this.program = ShaderLinkHelper.getStaticShaderLinkHelper().createProgram();
            ShaderLinkHelper.getStaticShaderLinkHelper().linkProgram(this);
            this.setupUniforms();
            if (this.attributes != null) {
                for (String s2 : this.attributes) {
                    int l = OpenGlHelper.glGetAttribLocation(this.program, s2);
                    this.attribLocations.add(l);
                }
            }
        }
        catch (Exception exception3) {
            JsonException jsonexception = JsonException.forException(exception3);
            jsonexception.setFilenameAndFlush(resourcelocation.getResourcePath());
            throw jsonexception;
        }
        finally {
            IOUtils.closeQuietly((Closeable)iresource);
        }
        this.markDirty();
    }

    public void deleteShader() {
        ShaderLinkHelper.getStaticShaderLinkHelper().deleteShader(this);
    }

    public void endShader() {
        OpenGlHelper.glUseProgram(0);
        currentProgram = -1;
        staticShaderManager = null;
        lastCull = true;
        for (int i = 0; i < this.shaderSamplerLocations.size(); ++i) {
            if (this.shaderSamplers.get(this.samplerNames.get(i)) == null) continue;
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + i);
            GlStateManager.bindTexture(0);
        }
    }

    public void useShader() {
        this.isDirty = false;
        staticShaderManager = this;
        this.blendingMode.apply();
        if (this.program != currentProgram) {
            OpenGlHelper.glUseProgram(this.program);
            currentProgram = this.program;
        }
        if (this.useFaceCulling) {
            GlStateManager.enableCull();
        } else {
            GlStateManager.disableCull();
        }
        for (int i = 0; i < this.shaderSamplerLocations.size(); ++i) {
            if (this.shaderSamplers.get(this.samplerNames.get(i)) == null) continue;
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + i);
            GlStateManager.enableTexture2D();
            Object object = this.shaderSamplers.get(this.samplerNames.get(i));
            int j = -1;
            if (object instanceof Framebuffer) {
                j = ((Framebuffer)object).framebufferTexture;
            } else if (object instanceof ITextureObject) {
                j = ((ITextureObject)object).getGlTextureId();
            } else if (object instanceof Integer) {
                j = (Integer)object;
            }
            if (j == -1) continue;
            GlStateManager.bindTexture(j);
            OpenGlHelper.glUniform1i(OpenGlHelper.glGetUniformLocation(this.program, this.samplerNames.get(i)), i);
        }
        for (ShaderUniform shaderuniform : this.shaderUniforms) {
            shaderuniform.upload();
        }
    }

    public void markDirty() {
        this.isDirty = true;
    }

    @Nullable
    public ShaderUniform getShaderUniform(String name) {
        return this.mappedShaderUniforms.get(name);
    }

    public ShaderUniform getShaderUniformOrDefault(String name) {
        ShaderUniform shaderuniform = this.getShaderUniform(name);
        return shaderuniform == null ? DEFAULT_SHADER_UNIFORM : shaderuniform;
    }

    private void setupUniforms() {
        int i = 0;
        int j = 0;
        while (i < this.samplerNames.size()) {
            String s = this.samplerNames.get(i);
            int k = OpenGlHelper.glGetUniformLocation(this.program, s);
            if (k == -1) {
                LOGGER.warn("Shader {}could not find sampler named {} in the specified shader program.", (Object)this.programFilename, (Object)s);
                this.shaderSamplers.remove(s);
                this.samplerNames.remove(j);
                --j;
            } else {
                this.shaderSamplerLocations.add(k);
            }
            ++i;
            ++j;
        }
        for (ShaderUniform shaderuniform : this.shaderUniforms) {
            String s1 = shaderuniform.getShaderName();
            int l = OpenGlHelper.glGetUniformLocation(this.program, s1);
            if (l == -1) {
                LOGGER.warn("Could not find uniform named {} in the specified shader program.", (Object)s1);
                continue;
            }
            this.shaderUniformLocations.add(l);
            shaderuniform.setUniformLocation(l);
            this.mappedShaderUniforms.put(s1, shaderuniform);
        }
    }

    private void parseSampler(JsonElement element) throws JsonException {
        JsonObject jsonobject = JsonUtils.getJsonObject(element, "sampler");
        String s = JsonUtils.getString(jsonobject, "name");
        if (!JsonUtils.isString(jsonobject, "file")) {
            this.shaderSamplers.put(s, null);
            this.samplerNames.add(s);
        } else {
            this.samplerNames.add(s);
        }
    }

    public void addSamplerTexture(String name, Object samplerTexture) {
        if (this.shaderSamplers.containsKey(name)) {
            this.shaderSamplers.remove(name);
        }
        this.shaderSamplers.put(name, samplerTexture);
        this.markDirty();
    }

    private void parseUniform(JsonElement element) throws JsonException {
        JsonObject jsonobject = JsonUtils.getJsonObject(element, "uniform");
        String s = JsonUtils.getString(jsonobject, "name");
        int i = ShaderUniform.parseType(JsonUtils.getString(jsonobject, "type"));
        int j = JsonUtils.getInt(jsonobject, "count");
        float[] afloat = new float[Math.max(j, 16)];
        JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "values");
        if (jsonarray.size() != j && jsonarray.size() > 1) {
            throw new JsonException("Invalid amount of values specified (expected " + j + ", found " + jsonarray.size() + ")");
        }
        int k = 0;
        for (JsonElement jsonelement : jsonarray) {
            try {
                afloat[k] = JsonUtils.getFloat(jsonelement, "value");
            }
            catch (Exception exception) {
                JsonException jsonexception = JsonException.forException(exception);
                jsonexception.prependJsonKey("values[" + k + "]");
                throw jsonexception;
            }
            ++k;
        }
        if (j > 1 && jsonarray.size() == 1) {
            while (k < j) {
                afloat[k] = afloat[0];
                ++k;
            }
        }
        int l = j > 1 && j <= 4 && i < 8 ? j - 1 : 0;
        ShaderUniform shaderuniform = new ShaderUniform(s, i + l, j, this);
        if (i <= 3) {
            shaderuniform.set((int)afloat[0], (int)afloat[1], (int)afloat[2], (int)afloat[3]);
        } else if (i <= 7) {
            shaderuniform.setSafe(afloat[0], afloat[1], afloat[2], afloat[3]);
        } else {
            shaderuniform.set(afloat);
        }
        this.shaderUniforms.add(shaderuniform);
    }

    public ShaderLoader getVertexShaderLoader() {
        return this.vertexShaderLoader;
    }

    public ShaderLoader getFragmentShaderLoader() {
        return this.fragmentShaderLoader;
    }

    public int getProgram() {
        return this.program;
    }

    static {
        currentProgram = -1;
        lastCull = true;
    }
}

