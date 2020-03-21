/**
 */
package fr.cea.nabla.ir.ir.impl;

import fr.cea.nabla.ir.ir.ConnectivityCall;
import fr.cea.nabla.ir.ir.IrPackage;
import fr.cea.nabla.ir.ir.ItemIndex;
import fr.cea.nabla.ir.ir.ItemIndexValueIterator;
import fr.cea.nabla.ir.ir.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.IteratorImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.IteratorImpl#getIndexValue <em>Index Value</em>}</li>
 *   <li>{@link fr.cea.nabla.ir.ir.impl.IteratorImpl#getContainer <em>Container</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IteratorImpl extends IterationBlockImpl implements Iterator {
	/**
	 * The cached value of the '{@link #getIndex() <em>Index</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected ItemIndex index;

	/**
	 * The cached value of the '{@link #getIndexValue() <em>Index Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexValue()
	 * @generated
	 * @ordered
	 */
	protected ItemIndexValueIterator indexValue;

	/**
	 * The cached value of the '{@link #getContainer() <em>Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected ConnectivityCall container;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IteratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.ITERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIndex getIndex() {
		if (index != null && index.eIsProxy()) {
			InternalEObject oldIndex = (InternalEObject)index;
			index = (ItemIndex)eResolveProxy(oldIndex);
			if (index != oldIndex) {
				InternalEObject newIndex = (InternalEObject)index;
				NotificationChain msgs = oldIndex.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__INDEX, null, null);
				if (newIndex.eInternalContainer() == null) {
					msgs = newIndex.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__INDEX, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.ITERATOR__INDEX, oldIndex, index));
			}
		}
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemIndex basicGetIndex() {
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIndex(ItemIndex newIndex, NotificationChain msgs) {
		ItemIndex oldIndex = index;
		index = newIndex;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IrPackage.ITERATOR__INDEX, oldIndex, newIndex);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIndex(ItemIndex newIndex) {
		if (newIndex != index) {
			NotificationChain msgs = null;
			if (index != null)
				msgs = ((InternalEObject)index).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__INDEX, null, msgs);
			if (newIndex != null)
				msgs = ((InternalEObject)newIndex).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__INDEX, null, msgs);
			msgs = basicSetIndex(newIndex, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.ITERATOR__INDEX, newIndex, newIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ItemIndexValueIterator getIndexValue() {
		if (indexValue != null && indexValue.eIsProxy()) {
			InternalEObject oldIndexValue = (InternalEObject)indexValue;
			indexValue = (ItemIndexValueIterator)eResolveProxy(oldIndexValue);
			if (indexValue != oldIndexValue) {
				InternalEObject newIndexValue = (InternalEObject)indexValue;
				NotificationChain msgs =  oldIndexValue.eInverseRemove(this, IrPackage.ITEM_INDEX_VALUE_ITERATOR__ITERATOR, ItemIndexValueIterator.class, null);
				if (newIndexValue.eInternalContainer() == null) {
					msgs =  newIndexValue.eInverseAdd(this, IrPackage.ITEM_INDEX_VALUE_ITERATOR__ITERATOR, ItemIndexValueIterator.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.ITERATOR__INDEX_VALUE, oldIndexValue, indexValue));
			}
		}
		return indexValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ItemIndexValueIterator basicGetIndexValue() {
		return indexValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIndexValue(ItemIndexValueIterator newIndexValue, NotificationChain msgs) {
		ItemIndexValueIterator oldIndexValue = indexValue;
		indexValue = newIndexValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IrPackage.ITERATOR__INDEX_VALUE, oldIndexValue, newIndexValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIndexValue(ItemIndexValueIterator newIndexValue) {
		if (newIndexValue != indexValue) {
			NotificationChain msgs = null;
			if (indexValue != null)
				msgs = ((InternalEObject)indexValue).eInverseRemove(this, IrPackage.ITEM_INDEX_VALUE_ITERATOR__ITERATOR, ItemIndexValueIterator.class, msgs);
			if (newIndexValue != null)
				msgs = ((InternalEObject)newIndexValue).eInverseAdd(this, IrPackage.ITEM_INDEX_VALUE_ITERATOR__ITERATOR, ItemIndexValueIterator.class, msgs);
			msgs = basicSetIndexValue(newIndexValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.ITERATOR__INDEX_VALUE, newIndexValue, newIndexValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectivityCall getContainer() {
		if (container != null && container.eIsProxy()) {
			InternalEObject oldContainer = (InternalEObject)container;
			container = (ConnectivityCall)eResolveProxy(oldContainer);
			if (container != oldContainer) {
				InternalEObject newContainer = (InternalEObject)container;
				NotificationChain msgs = oldContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__CONTAINER, null, null);
				if (newContainer.eInternalContainer() == null) {
					msgs = newContainer.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__CONTAINER, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.ITERATOR__CONTAINER, oldContainer, container));
			}
		}
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectivityCall basicGetContainer() {
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainer(ConnectivityCall newContainer, NotificationChain msgs) {
		ConnectivityCall oldContainer = container;
		container = newContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IrPackage.ITERATOR__CONTAINER, oldContainer, newContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainer(ConnectivityCall newContainer) {
		if (newContainer != container) {
			NotificationChain msgs = null;
			if (container != null)
				msgs = ((InternalEObject)container).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__CONTAINER, null, msgs);
			if (newContainer != null)
				msgs = ((InternalEObject)newContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__CONTAINER, null, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.ITERATOR__CONTAINER, newContainer, newContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IrPackage.ITERATOR__INDEX_VALUE:
				if (indexValue != null)
					msgs = ((InternalEObject)indexValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IrPackage.ITERATOR__INDEX_VALUE, null, msgs);
				return basicSetIndexValue((ItemIndexValueIterator)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IrPackage.ITERATOR__INDEX:
				return basicSetIndex(null, msgs);
			case IrPackage.ITERATOR__INDEX_VALUE:
				return basicSetIndexValue(null, msgs);
			case IrPackage.ITERATOR__CONTAINER:
				return basicSetContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.ITERATOR__INDEX:
				if (resolve) return getIndex();
				return basicGetIndex();
			case IrPackage.ITERATOR__INDEX_VALUE:
				if (resolve) return getIndexValue();
				return basicGetIndexValue();
			case IrPackage.ITERATOR__CONTAINER:
				if (resolve) return getContainer();
				return basicGetContainer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.ITERATOR__INDEX:
				setIndex((ItemIndex)newValue);
				return;
			case IrPackage.ITERATOR__INDEX_VALUE:
				setIndexValue((ItemIndexValueIterator)newValue);
				return;
			case IrPackage.ITERATOR__CONTAINER:
				setContainer((ConnectivityCall)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IrPackage.ITERATOR__INDEX:
				setIndex((ItemIndex)null);
				return;
			case IrPackage.ITERATOR__INDEX_VALUE:
				setIndexValue((ItemIndexValueIterator)null);
				return;
			case IrPackage.ITERATOR__CONTAINER:
				setContainer((ConnectivityCall)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IrPackage.ITERATOR__INDEX:
				return index != null;
			case IrPackage.ITERATOR__INDEX_VALUE:
				return indexValue != null;
			case IrPackage.ITERATOR__CONTAINER:
				return container != null;
		}
		return super.eIsSet(featureID);
	}

} //IteratorImpl
