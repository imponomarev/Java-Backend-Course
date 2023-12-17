package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;


public class Fibonacci implements ByteCodeAppender {

    public static final String  CLASS_NAME = "FibonacciImpl";

    public static final String  METHOD_NAME = "fib";

    public static final String  VALUES_TYPES = "(I)I";


    @SuppressWarnings("MagicNumber")
    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor mv,
        @NotNull Implementation.Context context,
        @NotNull MethodDescription md
    )  {

        mv.visitCode();

        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitLineNumber(5, l0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_1);

        Label l1 = new Label();
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, l1);

        Label l2 = new Label();
        mv.visitLabel(l2);
        mv.visitLineNumber(6, l2);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitLabel(l1);
        mv.visitLineNumber(8, l1);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, VALUES_TYPES, false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, VALUES_TYPES, false);
        mv.visitInsn(Opcodes.IADD);
        mv.visitInsn(Opcodes.IRETURN);

        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitLocalVariable("n", "I", null, l0, l3, 1);
        mv.visitMaxs(4, 2);
        mv.visitEnd();

        return new Size(4, 2);

    }
}
